import { useEffect, useState } from "react";
import RecipeCard from '../Recipe/RecipeCard'
import './RecipeStyle.css'
import { useParams } from "react-router";

function ShowRecipesByTags () {
    const {id} = useParams();
    const [tagoviRecepta, setTagoviRecepta] = useState([]);
    const [isLoading, setLoading] = useState(true);
  
    useEffect(() => {
        let url="http://localhost:8080/tagoviRecepta/tag/" + id
        
        fetch(url)
            .then((response) => {
            response.json().then((result) => {
                setTagoviRecepta(result);
                console.log(result)
            });
            })
            .finally(() => {
            setLoading(false);
            });
    
    }, []);    

    return (
        <div id="div-prikaz">
            <div className="prikaz-recepata-dva">
                {tagoviRecepta.length>0 && tagoviRecepta.map(tr => ( <RecipeCard recept={tr.recept} /> ))}
            </div>
        </div>
    );
}

export default ShowRecipesByTags;