import { useState } from "react";
import { useMutation } from "react-query";
import { postData } from "../../api/api.js";

function addFishkindForm() {
  const [fishName, setFishName] = useState("");
  const [fishInformation, setFishInformation] = useState("");
  const [water, setWater] = useState("");
  const [bait, setBait] = useState("");
  const [edibleChecked, setEdibleChecked] = useState(false);
  const [notEdibleChecked, setNotEdibleChecked] = useState(false);
  const [freshwaterChecked, setFreshwaterChecked] = useState(false);
  const [saltwaterChecked, setSaltwaterChecked] = useState(false);

  const { mutate } = useMutation((data) => postData('/fishdex', data));

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!notEdibleChecked && !edibleChecked) {
      alert("Bitte geben Sie an, ob der Fisch essbar ist.");
      return;
    }
    if (!freshwaterChecked && !saltwaterChecked) {
      alert("Bitte geben Sie an, in welchem Wasser der Fisch zu finden ist.");
      return;
    }
    const data = {
      id: math.random(),
      name: fishName,
      extraInfo: fishInformation,
      water: water,
      bait: bait,
      edible: edibleChecked,
      count: 0,
    };
    mutate(data);
  };

  const handleName = (event) => {
    setFishName(event.target.value);
  };

  const handleBait = (event) => {
    setBait(event.target.value);
  };

  const handleWater = (event) => {
    setWater(event.target.value);
  };

  const handleInfos = (event) => {
    setFishInformation(event.target.value);
  };

  const handleEdibleChange = (event) => {
    setEdibleChecked(event.target.checked);
    if (event.target.checked) {
      setNotEdibleChecked(false);
    }
  };

  const handleNotEdibleChange = (event) => {
    setNotEdibleChecked(event.target.checked);
    if (event.target.checked) {
      setEdibleChecked(false);
    }
  };

  const handleFreshwaterChange = (event) => {
    setFreshwaterChecked(event.target.checked);
    if (event.target.checked) {
      setSaltwaterChecked(false);
    }
  };

  const handleSaltwaterChange = (event) => {
    setSaltwaterChecked(event.target.checked);
    if (event.target.checked) {
      setFreshwaterChecked(false);
    }
  };

  return (
    <div className="section fish-form">
      <div className="container">
        <form onSubmit={handleSubmit}>
          <div className="row">
            <input type="file" accept="image/*" id="fish-img" name="fishImage" />
          </div>
          <div className="row">
            <label>Fischart</label>
            <input name="fish-kind" onChange={handleName} />
          </div>
          <div className="row">
            <label>Informationen über den Fisch</label>
            <input name="fish-info" onChange={handleInfos} />
          </div>
          <div className="row">
            <label>gewässer</label>
          </div>
          <div className="row">
            <label>
              Süßwasser
              <input
                type="checkbox"
                name="freshwater"
                onChange={handleFreshwaterChange}
              />
            </label>
            <label>
              Salzwasser
              <input
                type="checkbox"
                name="saltwater"
                onChange={handleSaltwaterChange}
              />
            </label>
          </div>
          <div className="row">
            <label>Essbar</label>
          </div>
          <div className="row">
            <label>
              Ja
              <input
                type="checkbox"
                name="edible"
                checked={edibleChecked}
                onChange={handleEdibleChange}
              />
            </label>
            <label>
              Nein
              <input
                type="checkbox"
                name="notEdible"
                checked={notEdibleChecked}
                onChange={handleNotEdibleChange}
              />
            </label>
          </div>
          <div className="row">
            <label>Geeignete Köder</label>
            <input name="bait" onChange={handleBait} />
          </div>
          <button type="submit">Fischart anlegen</button>
        </form>
      </div>
    </div>
  );
}

export default addFishkindForm;
