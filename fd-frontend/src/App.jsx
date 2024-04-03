import TestComponent from './Components/TestComponent'
import './css/styles.css'
import Header from "./js/header/header.jsx";
import Footer from "./js/footer/footer.jsx";
import FishdexOverview from "./js/general-components/fishdex-overview.jsx";

function App() {
  return (
    <>
      <div>
        <Header/>
        <FishdexOverview/>
        <Footer/>
      </div>
    </>
  )
}

export default App
