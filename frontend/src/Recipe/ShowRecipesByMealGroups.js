import { useEffect, useState } from "react";
import RecipeCard from '../Recipe/RecipeCard'
import './RecipeStyle.css'
import { useParams } from "react-router";

function ShowRecipesByMealGroups () {
    const {id} = useParams();
    const [grupeJelaRecepta, setGrupeJelaRecepta] = useState([]);
    const [isLoading, setLoading] = useState(true);
  
    useEffect(() => {
        let url="http://localhost:8080/grupeJelaRecepta/grupaJela/" + id
    
        fetch(url)
            .then((response) => {
            response.json().then((result) => {
                setGrupeJelaRecepta(result);
                console.log(result)
            });
            })
            .finally(() => {
            setLoading(false);
            });
    
    }, [id]);    

    return (
        <div id="div-prikaz"> 
            <div className="prikaz-recepata-dva">
                {grupeJelaRecepta.length>0 && grupeJelaRecepta.map(grj => ( <RecipeCard recept={grj.recept} /> ))}
            </div>
        </div>
    );
}

export default ShowRecipesByMealGroups;