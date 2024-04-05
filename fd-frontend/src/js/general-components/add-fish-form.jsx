function addFishkindForm(){
 function submitHandler(){

 }
  return(
    <div className="section fish-form">
      <div className="container">
        <form onSubmit={submitHandler}>
          <div className="row">
            <label>
              Fischart
            </label>
            <input name="fish-kind"/>
          </div>
          <div className="row">
            <label>
              Informationen über den Fisch
            </label>
            <input name="fish-info"/>
          </div>
          <div className="row">
            bool
          </div>
          <div className="row">
bool
          </div>
          <div className="row">
            <label>
              Geeignete Köder
            </label>
            <input name="bait"/>
          </div>
          <button type="submit">Fischart anlegen</button>
        </form>
      </div>
    </div>
  )
}

export default addFishkindForm;