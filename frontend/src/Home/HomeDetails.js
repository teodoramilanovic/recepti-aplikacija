import RecipeCard from '../Recipe/RecipeCard'
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import './HomeStyle.css'

function HomeDetails() {

  const [noviRecepti, setNoviRecepti] = useState([]);
  const [popularniRecepti, setPopularniRecepti] = useState([]);
  const [isLoading, setLoading] = useState(true);

  useEffect(() => {

    const url1="http://localhost:8080/recepti/popularni"
    const url2="http://localhost:8080/recepti/novi"

    fetch(url1)
      .then((response) => {
        response.json().then((result) => {
          setPopularniRecepti(result);
        });
      })
      .finally(() => {
        setLoading(false);
      });

      fetch(url2)
      .then((response) => {
        response.json().then((result) => {
          setNoviRecepti(result);
        });
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);


    return (
      <div>
        <div className="recepti">
          <p className="titles">Popularni recepti</p>
          <Link to={`/popularni`}>
            <button className="prikazi-vise">Prikazi vise</button>
          </Link>
        </div>
        <div className="prikaz-recepata">
        {popularniRecepti.length>0 && popularniRecepti.slice(0,10).map(recept => ( <RecipeCard recept={recept} /> ))}
        </div>
        <div className="recepti">
          <p className="titles">Novi recepti</p>
          <Link to={`/novi`}>
            <button className="prikazi-vise">Prikazi vise</button>
          </Link>
        </div>
        <div className="prikaz-recepata">
        {noviRecepti.length>0 && noviRecepti.slice(0,10).map(recept => ( <RecipeCard recept={recept} /> ))}
        </div>
      </div>
    );
  }
  
  export default HomeDetails;