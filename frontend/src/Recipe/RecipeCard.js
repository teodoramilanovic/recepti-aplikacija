import './RecipeStyle.css'
import { Link } from "react-router-dom";

function RecipeCard({recept}) {
    return (
        <Link to={`/recepti/${recept.id}`} style={{textDecoration: 'none', color: 'black'}}>
            <div className="card">
                {recept.fotografija!== "" && <img id="slicica" src={recept.fotografija} alt={recept.naziv}></img>}
                {recept.fotografija=== "" && <img id="slicica" src="https://www.generationsforpeace.org/wp-content/uploads/2018/03/empty.jpg"></img>}
                <div className="container">
                    <h4><b>{recept.naziv}</b></h4>
                    <p>{recept.korisnik.ime + " " + recept.korisnik.prezime}</p>
                </div>
            </div>
        </Link>
    );
}

export default RecipeCard;