import { useEffect, useState } from "react";
import RecipeCard from '../Recipe/RecipeCard'
import Title from './Title'
import './RecipeStyle.css'
import { useParams } from "react-router";

function ShowRecipes ({vrsta}) {
    const {naziv} = useParams();
    const [recepti, setRecepti] = useState([]);
    const [isLoading, setLoading] = useState(true);
  
    useEffect(() => {
        let url="http://localhost:8080/recepti/popularni"
        if (vrsta === "novi")
            url="http://localhost:8080/recepti/novi"
        else if (vrsta === "trazi")
            url="http://localhost:8080/recepti/trazi/" + naziv
    
        fetch(url)
            .then((response) => {
            response.json().then((result) => {
                setRecepti(result);
            });
            })
            .finally(() => {
            setLoading(false);
            });
    
    }, [naziv]);    

    return (
        <div id="div-prikaz"> 
            <Title vrsta={vrsta} />
            <div className="prikaz-recepata-dva">
                {recepti.length>0 && recepti.map(recept => ( <RecipeCard recept={recept} /> ))}
            </div>
        </div>
    );
}

export default ShowRecipes;