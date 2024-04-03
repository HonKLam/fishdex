import React from 'react'

function fishdexEntry(data) {

  return(
    <div className="fishdex-entry">
      <div className="img-wrap">
        <img src="https://placehold.co/600x400"/>
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