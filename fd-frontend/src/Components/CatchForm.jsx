import { useState } from 'react'
import { useMutation, useQuery } from 'react-query'
import { fetchData, postData } from '../utils/api'
import Button from './General/Button.jsx'

export default function CatchForm() {
  const { data, isLoading, isError } = useQuery('data', () =>
    fetchData('/fishdex')
  )

  const [fishName, setFishName] = useState('')
  const [description, setDescription] = useState('')
  const [location, setLocation] = useState('')
  const [file, setFile] = useState(null)
  const [fileUploaded, setFileUploaded] = useState(false)
  const [weight, setWeight] = useState()
  const [length, setLength] = useState()
  const [time, setTime] = useState('')
  const [date, setDate] = useState('')
  const [selectedOption, setSelectedOption] = useState('')
  const [selectedId, setSelectedId] = useState('')

  const handleSelectChange = (event) => {
    const selectedName = event.target.value
    setSelectedOption(selectedName)

    // Find the corresponding ID based on the selected name
    const selectedObject = data.find((item) => item.name === selectedName)
    if (selectedObject) {
      setSelectedId(selectedObject.id)
    } else {
      setSelectedId('') // Reset the selected ID if the name is not found
    }
  }
  const { mutate } = useMutation((data) => postData('/catch', data))

  const handleFileChange = (event) => {
    const selectedFile = event.target.files[0]
    const reader = new FileReader()
    reader.onload = () => {
      const base64Data = reader.result.split(',')[1] // Extract base64 data
      setFile(base64Data) // Set base64 data to state
      setFileUploaded(true)
    }
    reader.readAsDataURL(selectedFile) // Read file as data URL (base64)
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    const data = {
      catchDate: formatDateAndTime(date, time),
      location: location,
      catchImage: file,
      length: length,
      weight: weight,
      description: description,
      fishId: selectedId,
    }
    mutate(data)
    window.location.pathname = '/timeline'
  }
  function formatDateAndTime(inputDate, inputTime) {
    // Split the input date and time
    const [day, month, year] = inputDate.split('-')
    const [hours, minutes] = inputTime.split(':')

    // Create a new Date object using the provided date and time
    const formattedDate = new Date(year, month - 1, day, hours, minutes)

    // Get the components of the formatted date
    const formattedYear = formattedDate.getFullYear()
    const formattedMonth = String(formattedDate.getMonth() + 1).padStart(
      2,
      '0'
    )
    const formattedDay = String(formattedDate.getDate()).padStart(2, '0')
    const formattedHours = String(formattedDate.getHours()).padStart(2, '0')
    const formattedMinutes = String(formattedDate.getMinutes()).padStart(
      2,
      '0'
    )
    const formattedSeconds = String(formattedDate.getSeconds()).padStart(
      2,
      '0'
    )

    // Get the time zone offset in minutes and convert it to hours and minutes
    const timeZoneOffset = formattedDate.getTimezoneOffset()
    const offsetHours = Math.floor(Math.abs(timeZoneOffset) / 60)
    const offsetMinutes = Math.abs(timeZoneOffset) % 60
    const offsetSign = timeZoneOffset < 0 ? '+' : '-'

    // Construct the formatted date and time string
    const formattedDateTime = `${formattedYear}-${formattedMonth}-${formattedDay}T${formattedHours}:${formattedMinutes}:${formattedSeconds}${offsetSign}${String(offsetHours).padStart(2, '0')}:${String(offsetMinutes).padStart(2, '0')}`

    return formattedDateTime
  }
  const handleComment = (event) => {
    setDescription(event.target.value)
  }
  const handleDate = (event) => {
    setDate(event.target.value)
  }
  const handleTime = (event) => {
    setTime(event.target.value)
  }
  const handleWeight = (event) => {
    setWeight(event.target.value)
  }
  const handleLength = (event) => {
    setLength(event.target.value)
  }
  const handleLocation = (event) => {
    setLocation(event.target.value)
  }

  if (isLoading) return <div>Laden...</div>
  if (isError) return <div>Ein Fehler ist aufgetreten!</div>

  return (
    <div className="section catch-form">
      <div className="container">
        <form onSubmit={handleSubmit}>
          <div className="row file">
            <img
              src="https://placehold.co/600x400"
              className="image"
            />
            <div className="file file--upload">
              <label
                htmlFor="input-file"
                className={fileUploaded ? 'finished' : ''}
              >
                <i className="material-icons">
                  {fileUploaded
                    ? 'Bild wurde erfolgreich hochgeladen'
                    : 'Bild Hochladen'}
                </i>
              </label>
              <input
                id="input-file"
                type="file"
                onChange={handleFileChange}
              />
            </div>
          </div>
          <div className="row row-col">
            <label>Kommentar</label>
            <textarea
              className="fish-comment"
              name="fish-comment"
              onChange={handleComment}
            />
          </div>
          <div className="row row-col">
            <label>Wo wurde der Fisch gefangen?</label>
            <input
              className="location"
              name="location"
              onChange={handleLocation}
            />
          </div>
          <div className="row double">
            <div className="item-wrap">
              <label>Datum</label>
              <input
                className="date"
                name="date"
                placeholder="DD-MM-YYYY"
                alt="Format: DD-MM-YYYY"
                onChange={handleDate}
              />
            </div>
            <div className="item-wrap">
              <label>Uhrzeit</label>
              <input
                className="time"
                name="time"
                placeholder="13:00"
                alt="Format: 13:00"
                onChange={handleTime}
              />
            </div>

          </div>
          <div className="dropdown">
            <label htmlFor="dropdown">Welche Fishcart haben Sie gefangen?</label>
            <select
              id="dropdown"
              value={selectedOption}
              onChange={handleSelectChange}
            >
              <option value="">Wähle eine option</option>
              {data.map((item, index) => (
                <option key={index} value={item.name}>
                  {item.name}
                </option>
              ))}
            </select>
          </div>
          <div className="row double">
            <div className="item-wrap">
              <label>Gewicht in Kg</label>
              <input
                className="weight"
                name="weight"
                onChange={handleWeight}
              />
            </div>
            <div className="item-wrap">
              <label>Länge in cm</label>
              <input
                className="length"
                name="length"
                onChange={handleLength}
              />
            </div>
          </div>
          <div className="submit-btn">
            <Button
              text="Beitrag anlegen"
              callBack={handleSubmit}
            />
          </div>
        </form>
      </div>
    </div>
  )
}
