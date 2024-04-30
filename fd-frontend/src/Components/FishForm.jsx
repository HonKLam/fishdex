import { useState } from 'react'
import { useMutation, useQuery } from 'react-query'
import { fetchData, postData } from '../utils/api'
import Button from './General/Button.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import ReactDOM from 'react-dom/client'

export default function FishForm() {
  const [fishName, setFishName] = useState('')
  const [fishInformation, setFishInformation] = useState('')
  const [water, setWater] = useState('')
  const [bait, setBait] = useState('')
  const [edibleChecked, setEdibleChecked] = useState(false)
  const [notEdibleChecked, setNotEdibleChecked] = useState(false)
  const [freshwaterChecked, setFreshwaterChecked] = useState(false)
  const [saltwaterChecked, setSaltwaterChecked] = useState(false)
  const [file, setFile] = useState(null)
  const [fileUploaded, setFileUploaded] = useState(false)

  const { mutate } = useMutation((data) => postData('/fish', data))

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
    if (!notEdibleChecked && !edibleChecked) {
      alert('Bitte geben Sie an, ob der Fisch essbar ist.')
      return
    }
    if (!freshwaterChecked && !saltwaterChecked) {
      alert(
        'Bitte geben Sie an, in welchem Wasser der Fisch zu finden ist.'
      )
      return
    }
    if (!fishName) {
      alert('Bitte geben Sie einen Fischnamen an.')
      return
    }
    const data = {
      name: fishName,
      extraInfo: fishInformation,
      water: water,
      bait: bait,
      edible: edibleChecked,
      counter: 0,
      fishImage: file,
    }
    mutate(data)
    window.location.pathname = '/fishdex'
  }

  const handleName = (event) => {
    setFishName(event.target.value)
  }

  const handleBait = (event) => {
    setBait(event.target.value)
  }

  const handleWater = (event) => {
    setWater(event.target.value)
  }

  const handleInfos = (event) => {
    setFishInformation(event.target.value)
  }

  const handleEdibleChange = (event) => {
    setEdibleChecked(event.target.checked)
    if (event.target.checked) {
      setNotEdibleChecked(false)
    }
  }

  const handleNotEdibleChange = (event) => {
    setNotEdibleChecked(event.target.checked)
    if (event.target.checked) {
      setEdibleChecked(false)
    }
  }

  const handleFreshwaterChange = (event) => {
    setFreshwaterChecked(event.target.checked)
    if (event.target.checked) {
      setSaltwaterChecked(false)
    }
  }

  const handleSaltwaterChange = (event) => {
    setSaltwaterChecked(event.target.checked)
    if (event.target.checked) {
      setFreshwaterChecked(false)
    }
  }

  return (
    <div className="section fish-form">
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
            <label className="required">Fischart:</label>
            <input
              className="fish-name"
              name="fish-kind"
              onChange={handleName}
            />
          </div>
          <div className="row row-col">
            <label>Informationen über die Fischart:</label>
            <textarea
              className="fish-info"
              name="fish-info"
              onChange={handleInfos}
            />
          </div>
          <div className="row">
            <label className="required">
              In welchen Gewässern ist der Fisch zu finden?
            </label>
          </div>
          <div className="row">
            <label>
              Süßwasser
              <input
                type="checkbox"
                name="freshwater"
                onChange={handleFreshwaterChange}
              />
            </label>
            <label>
              Salzwasser
              <input
                type="checkbox"
                name="saltwater"
                onChange={handleSaltwaterChange}
              />
            </label>
          </div>
          <div className="row">
            <label className="required">
              Ist der Fisch essbar?
            </label>
          </div>
          <div className="row">
            <label>
              Ja
              <input
                type="checkbox"
                name="edible"
                checked={edibleChecked}
                onChange={handleEdibleChange}
              />
            </label>
            <label>
              Nein
              <input
                type="checkbox"
                name="notEdible"
                checked={notEdibleChecked}
                onChange={handleNotEdibleChange}
              />
            </label>
          </div>
          <div className="row row-col">
            <label>Geeignete Köder für den Fisch</label>
            <input
              className="bait"
              name="bait"
              onChange={handleBait}
            />
          </div>
          <div className="submit-btn">
            <Button
              text="Fischart anlegen"
              callBack={handleSubmit}
            />
          </div>
        </form>
      </div>
    </div>
  )
}
