import { useEffect, useState } from "react";
import RecipeCard from '../Recipe/RecipeCard'
import '../Recipe/RecipeStyle.css'
import { useParams } from "react-router";
import useAuth from '../hooks/useAuth'

function ShowKolekcija () {
    const {id} = useParams();
    const {auth} = useAuth();
    const [recepti, setRecepti] = useState([]);
    const [isLoading, setLoading] = useState(true);
  
    useEffect(() => {
        let url="http://localhost:8080/omiljeniRecepti/kolekcija/"+id
        const requestOptions = {
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`}
          };
        
        fetch(url,requestOptions)
            .then((response) => {
            response.json().then((result) => {
                setRecepti(result);
            });
            })
            .finally(() => {
            setLoading(false);
            });
    
    }, [id]);  

    return (
        <div id="div-prikaz">
            <div className="prikaz-recepata-dva">
                {recepti.length>0 && recepti.map(rec => ( <RecipeCard recept={rec.recept} /> ))}
            </div>
        </div>
    );
}

export default ShowKolekcija;