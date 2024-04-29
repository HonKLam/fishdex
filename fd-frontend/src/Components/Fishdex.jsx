import { useQuery } from 'react-query'
import { fetchData } from '../utils/api.js'
import FishdexEntry from './FishdexEntry.jsx'
import Button from "./General/Button.jsx";

export default function Fishdex() {
  const { data, isLoading, isError } = useQuery('data', () =>
    fetchData('/fishdex')
  )
  if (isLoading !== true && isError !== true) {
    console.log(data)
  }
  if (isLoading) return <div>Laden...</div>
  if (isError) return <div>Ein Fehler ist aufgetreten!</div>
  function handleClick() {
    window.location.pathname = '/fish/form'
  }
  return (
    <div className="section fishdex-overview">
      <div className="container">
        <div className="row">
          <div className="col-16 text-wrap">
            <div className="head-wrap">
              <h1 className="heading">Fishdex</h1>
              <Button text="Fischart Hinzufügen" callBack={handleClick}/>
            </div>
            <p className="description">
            hier kommt dann ein erklärtext rein Jemand musste
              Josef K. verleumdet haben, denn ohne dass er etwas
              Böses getan hätte, wurde er eines Morgens verhaftet.
              »Wie ein Hund!« sagte er, es war, als sollte die
              Scham ihn überleben. Als Gregor Samsa eines Morgens
              aus unruhigen Träumen erwachte, fand er sich in
              seinem Bett zu einem ungeheueren Ungeziefer
              verwandelt. Und es war ihnen wie eine Bestätigung
              ihrer neuen Träume und guten Absichten, als am Ziele
              ihrer Fahrt die Tochter als erste sich erhob und
              ihren jungen Körper dehnte. »Es ist ein
              eigentümlicher Apparat«, sagte der Offizier zu dem
              Forschungsreisenden und überblickte mit einem
              gewissermaßen bewundernden Blick den ihm doch
              wohlbekannten Apparat.
            </p>
          </div>
        </div>
        <div className="card-wrap">
          {data.map((item, index) => {
            return (
              <a className="fishdex-entry" key={index}>
                <div className="img-wrap">
                  {item.imgURL && <img src={item.imgURL} />}
                </div>
                <div className="text-wrap">
                  {item.name && item.name}
                </div>
              </a>
            )
          })}
        </div>
      </div>
    </div>
  )
}
