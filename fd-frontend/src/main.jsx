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
import FishInfo, {
    loader as fishLoader,
} from './Components/Fishdex/FishInfo.jsx'
import CatchForm from './Components/CatchForm.jsx'

const queryClient = new QueryClient()

/*
 * Routing
 *
 * Wir nutzen React-Router-Dom um unterschiedliche Components/Seiten durch Pfade
 * zu verbinden.
 *
 * */
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
            {
                path: '/timeline/form',
                element: <CatchForm />,
            },
            {
                path: '/fishdex/:fishId',
                element: <FishInfo />,
                loader: fishLoader,
            },
        ],
    },
])

/*
 * Von hier aus entstehen alle weiteren Componenten
 *
 */
ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <QueryClientProvider client={queryClient}>
            <RouterProvider router={router} />
        </QueryClientProvider>
    </React.StrictMode>
)
