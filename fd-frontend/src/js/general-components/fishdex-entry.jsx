import React from 'react'

function fishdexEntry(data) {
  return (
    <div className="fishdex-entry">
      <div className="img-wrap">
        {data.imgUrl &&
          <img src={data.imgUrl}/>
        }
      </div>
      <div className="text-wrap">
        {data.name &&
          data.name
        }
      </div>
    </div>
  )
}
export default fishdexEntry;