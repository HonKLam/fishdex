import { Outlet } from 'react-router-dom'
import Header from './Header'
import Footer from './Footer'
import '../../css/styles.css'

export default function App() {
  return (
    <div>
      <Header />
      <Outlet />
      <Footer />
    </div>
  )
}
