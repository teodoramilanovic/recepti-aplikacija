import { useEffect, useState } from "react";
import RecipeCard from '../Recipe/RecipeCard'
import useAuth from '../hooks/useAuth'
import '../Recipe/RecipeStyle.css'

function ShowHomePage () {
    const {auth} = useAuth();
    const [recepti, setRecepti] = useState([]);
    const [isLoading, setLoading] = useState(true);
  
    useEffect(() => {
        let url="http://localhost:8080/recepti/pocetna"

        const requestOptions = {
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`}
        };

        fetch(url, requestOptions)
            .then((response) => {
            response.json().then((result) => {
                setRecepti(result);
            });
            })
            .finally(() => {
            setLoading(false);
            });
    
    }, []);    

    return (
        <div id="div-prikaz">
            <div className="prikaz-recepata-dva">
                {recepti.length>0 && recepti.map(recept => ( <RecipeCard recept={recept} /> ))}
            </div>
        </div>
    );
}

export default ShowHomePage;