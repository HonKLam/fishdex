import TestComponent from './Components/TestComponent'
import './css/styles.css'
import Header from "./js/header/header.jsx";
import Footer from "./js/footer/footer.jsx";

function App() {
  return (
    <>
      <div>
        <Header/>
          geht das noch?
        <Footer/>
      </div>
      <TestComponent />
    </>
  )
}

export default App
