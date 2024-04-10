export default function FishdexEntry(data) {
    return (
        <div className="fishdex-entry">
            <div className="img-wrap">
                {data.imgUrl && <img src={data.imgUrl} />}
            </div>
            <div className="text-wrap">{data.name && data.name}</div>
        </div>
    )
}
