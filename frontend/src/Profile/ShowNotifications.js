import { useEffect, useState} from "react";
import useAuth from '../hooks/useAuth'
import { Link } from "react-router-dom";
import './ProfileStyle.css'

function ShowNotifications () {
    const {auth} = useAuth();
    const [obavjestenja, setObavjestenja] = useState([]);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {

        const url="http://localhost:8080/obavjestenja"
        
        const requestOptions = {
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`}
          };

        fetch(url, requestOptions)
          .then((response) => {
            response.json().then((result) => {
              setObavjestenja(result);
            });
          })
          .finally(() => {
            setLoading(false);
          });
    
      }, [auth]);
    
    return (
        <div id="o-container">
        {obavjestenja.length>0 && obavjestenja.map(o => (
            <div className="obavjestenje">
                <Link to={`/profil/${o.aktivniKorisnik.email}`} >
                  <img className="elem" id="slika-profila" src={o.aktivniKorisnik.avatar}/>
                </Link>
                <p className="elem" style={{color:'rgba(76, 35, 92, 0.877)',fontWeight:500}}>{o.aktivniKorisnik.ime}{" "}{o.aktivniKorisnik.prezime}</p>
                <p className="elem">{o.opis}</p>
                <p id="datum">{o.datum.substring(0,10)}{" "}{o.datum.substring(11,16)}</p>
            </div>
         ))}
        </div>
    );
}

export default ShowNotifications;