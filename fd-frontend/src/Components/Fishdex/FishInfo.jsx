import styles from '../../css/FishInfo.module.css'
import { useLoaderData } from 'react-router-dom'
import { fetchData } from '../../utils/api'
import { useQuery } from 'react-query'
import Button from '../General/Button'
import formatDate from '../../utils/date'

// eslint-disable-next-line
export function loader({ params }) {
  const fishId = params.fishId
  return { fishId }
}

function handleBack() {
  window.location.pathname = '/fishdex'
}

export default function FishInfo() {
  const { fishId } = useLoaderData()

  const { data, isLoading, isError } = useQuery('fish-info-data', () =>
    fetchData(`/fish/info/${fishId}`)
  )

  const catchList = useQuery('catch-list-data', () =>
    fetchData(`/catch/list/${fishId}`)
  )

  if (isLoading) return <div></div>
  if (isError) return <div></div>

  return (
    <div className={styles.fish_info_container}>
      <div className={styles.fish_info_inner_container}>
        <div className={styles.back_button}>
          <Button text="Zurück" callBack={handleBack} />
        </div>
        <h1 id={styles.title}>{data.name}</h1>
        <div className={styles.info_block}>
          <img src={data.imgURL} />
          <div className={styles.info_text}>
            <p id={styles.text}>{data.extraInfo}</p>
            <hr id={styles.line} />
            <p>{data.water}</p>
            <p>{data.edible ? 'Essbar' : 'Nicht Essbar'}</p>
            <p>Köder: {data.bait}</p>
            <p>Anzahl Gefangen: {data.counter}</p>
          </div>
        </div>
        <div id={styles.lastCatches}>
          <h3>Meine letzten Fänge</h3>
        </div>
        {catchList.data &&
          catchList.data.map((entry, index) => {
            return (
              <div
                key={index}
                className={styles.catch_info_container}
              >
                <img src={entry.imgUrl} />
                <div id={styles.catchText}>
                  <h1>{entry.location}</h1>
                  <p>{entry.length}cm</p>
                  <p>{entry.weight}kg</p>
                </div>
                <div id={styles.date}>
                  <h3>{formatDate(entry.catchDate)}</h3>
                </div>
              </div>
            )
          })}
      </div>
    </div>
  )
}
