import TestComponent from './Components/TestComponent'
import './css/styles.css'
import Header from "./js/header/header.jsx";
import Footer from "./js/footer/footer.jsx";
import FishdexOverview from "./js/general-components/fishdex-overview.jsx";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import FishdexEntry from "./js/general-components/fishdex-entry.jsx";
import NoPage from "./js/general-components/noPage.jsx";
import AddFishkindForm from "./js/general-components/add-fish-form.jsx";

function App() {
  return (
    <>
      <div>
        <Header/>
        <AddFishkindForm/>
        <Footer/>
      </div>
    </>
  )
}

export default App
