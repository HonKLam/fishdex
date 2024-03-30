import { fetchData } from '../api/api'
import { useQuery } from 'react-query'

export default function TestComponent() {
  // GET REQUESTS
  const { data, isLoading, isError } = useQuery('data', () =>
    fetchData('/testuser')
  )

  if (isLoading) return <div>Laden...</div>
  if (isError) return <div>Error</div>

  return (
    <div>
      {data.map((obj) => {
        return <div key={obj.name}>{obj.name}</div>
      })}
    </div>
  )
}
