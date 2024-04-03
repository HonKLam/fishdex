import React from 'react'
import {useQuery} from "react-query";
import {fetchData} from "../../api/api.js";
import FishdexEntry from "./fishdex-entry.jsx";

function fishdexOverview() {

  const { data, isLoading, isError } = useQuery('data', () =>
    fetchData('/fishdex')
  )
  return(
    <div className="section fishdex-overview">
      <div className="container">

      </div>
    </div>
  )
}
export default fishdexOverview;