import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'

import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { QueryClient, QueryClientProvider } from 'react-query'

import ErrorPage from './Components/General/ErrorPage.jsx'
import App from './Components/General/App.jsx'
import Fishdex from './Components/Fishdex.jsx'
import FishForm from './Components/FishForm.jsx'
import Homepage from './Components/Homepage/Homepage.jsx'
import Timeline from './Components/Timeline/Timeline.jsx'

const queryClient = new QueryClient()

const router = createBrowserRouter([
    {
        element: <App />,
        errorElement: <ErrorPage />,
        children: [
            {
                path: '/',
                element: <Homepage />,
            },
            {
                path: '/fishdex',
                element: <Fishdex />,
            },
            {
                path: '/fish/form',
                element: <FishForm />,
            },
            {
                path: '/timeline',
                element: <Timeline />,
            },
        ],
    },
])

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <QueryClientProvider client={queryClient}>
            <RouterProvider router={router} />
        </QueryClientProvider>
    </React.StrictMode>
)
