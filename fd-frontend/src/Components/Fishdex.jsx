import { useQuery } from 'react-query'
import { fetchData } from '../utils/api.js'
import Button from './General/Button.jsx'

export default function Fishdex() {
    const { data, isLoading, isError } = useQuery('data', () =>
        fetchData('/fishdex')
    )
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
                            <Button
                                text="Fischart Hinzufügen"
                                callBack={handleClick}
                            />
                        </div>
                    </div>
                </div>
                <div className="card-wrap">
                    {data.map((item, index) => {
                        return (
                            <a
                                href={`/fishdex/${item.id}`}
                                className="fishdex-entry"
                                key={index}
                            >
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
