import styles from '../../css/FishInfo.module.css'
import { useLoaderData } from 'react-router-dom'
import { fetchData } from '../../utils/api'
import { useQuery } from 'react-query'
import Button from '../General/Button'

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

  if (isLoading) return <div></div>
  if (isError) return <div></div>

  return (
    <div className={styles.fish_info_container}>
      <div className={styles.fish_info_inner_container}>
        <Button text="Zurück" callBack={handleBack} />
        <h1>{data.name}</h1>
        <div className={styles.info_block}>
          <img src="/assets/Salmon.png" />
          <div className={styles.info_text}>
            <p>
              Info zum Fish Info zum Fish Info zum Fish Info zum
              Fish Info zum Fish Info zum Fish Info zum Fish Info
              zum Fish Info zum Fish Info zum Fish Info zum Fish
              Info zum Fish
            </p>
            <p>Salzig (wie ich)</p>
            <p>Essbar</p>
            <p>Köder</p>
            <p>Anzahl Gefangen: 3</p>
          </div>
        </div>
      </div>
    </div>
  )
}
