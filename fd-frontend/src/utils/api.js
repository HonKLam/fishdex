const BACKEND_URL = 'http://localhost:8080'

// Function to fetch data from the backend API and return as a JS object
export async function fetchData(path) {
  try {
    const response = await fetch(`${BACKEND_URL}${path}`)
    if (!response.ok) {
      throw new Error('Failed to fetch data')
    }
    const data = await response.json()
    return data
  } catch (error) {
    console.error('Error fetching data:', error)
    throw error
  }
}

// Function to post data to the backend API and return the response as a JS object
export async function postData(path, body) {
  try {
    const response = await fetch(`${BACKEND_URL}${path}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    })
    if (!response.ok) {
      throw new Error('Failed to post data')
    }
    const data = await response.json()
    return data
  } catch (error) {
    console.error('Error posting data:', error)
    throw error
  }
}
