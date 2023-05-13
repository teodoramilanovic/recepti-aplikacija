import { useEffect, useState} from "react";
import { useParams } from "react-router";
import useAuth from '../hooks/useAuth'
import RecipeCard from '../Recipe/RecipeCard'
import './ProfileStyle.css'
import KolekcijaCard from './KolekcijaCard'
import { Link } from "react-router-dom";

function ShowProfile () {
    const {auth} = useAuth();
    const {email} = useParams();
    const [korisnik, setKorisnik] = useState({});
    const [kolekcije, setKolekcije] = useState([]);
    const [postoji, setPostoji] = useState(false);
    const [nazivKol, setNazivKol] = useState("");
    const [rerender, setRerender] = useState(false);
    const [isLoading, setLoading] = useState(true);
    const [mojiRecepti, setMojiRecepti] = useState(true);

    useEffect(() => {
      
      if(auth.res && email===auth.res.result.email){
        const url = "http://localhost:8080/korisnik/" + email
        const url1 = "http://localhost:8080/mojeKolekcije"
        
        const requestOptions = {
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`}
          };

        fetch(url, requestOptions)
          .then((response) => {
            response.json().then((result) => {
              setKorisnik(result);
            });
          })
          .finally(() => {
            setLoading(false);
          });
        
        fetch(url1, requestOptions)
          .then((response) => {
            response.json().then((result) => {
              setKolekcije(result);
            });
          })
          .finally(() => {
            setLoading(false);
          });
        

      }else if(auth.res){
        fun1().then((res) => {
          fun2(res)
        }).catch((e) => console.log(e.message))
      }
      
      }, [auth,email,rerender]);
    
    
    const fun1 = async () => {
      const url1 = "http://localhost:8080/korisnik/" + email

        const requestOptions = {
          headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`}
        };

        const response= await fetch(url1, requestOptions)
        if(!response.ok){
          throw new Error('data could not be fetched')
        }else{
          return response.json()
        }

    }    
    const fun2 = async (k) => {
      setKorisnik(k)
      const url2 = "http://localhost:8080/postojiPracenje/" + k.id
      const requestOptions = {
        headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`}
      };

      fetch(url2, requestOptions)
            .then((response) => {
              response.json().then((result) => {
                setPostoji(result);
                console.log(result)
              });
            })
    }
    
    const handleClickRecepti = () => {
        setMojiRecepti(true);
    }

    const handleClickSacuvani = () => {
        setMojiRecepti(false);
    }

    const handlePracenjeChange = () => {
        if(postoji){
          let url = "http://localhost:8080/pracenja/obrisi"
          let aktivniKorisnikId = auth.res.result.id
          let pasivniKorisnikId = korisnik.id
          let pracenje = {aktivniKorisnikId, pasivniKorisnikId}
          const requestOptions = {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` },
            body: JSON.stringify(pracenje),
          };

          fetch(url, requestOptions)
          setKorisnik(prethodni => ({...prethodni, brojPratioci: korisnik.brojPratioci-1}))

        }else{
          let url = "http://localhost:8080/pracenja"
          let aktivniKorisnikId = auth.res.result.id
          let pasivniKorisnikId = korisnik.id
          let pracenje = {aktivniKorisnikId, pasivniKorisnikId}
          const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` },
            body: JSON.stringify(pracenje),
          };
      
          fetch(url, requestOptions)
          setKorisnik(prethodni => ({...prethodni, brojPratioci: korisnik.brojPratioci+1}))

          let url2 = "http://localhost:8080/obavjestenja"
          let opis="Vas je zapratio/la."
          let aktivniKorisnikid = auth.res.result.id
          let obavjestenje = {opis, aktivniKorisnikid, pasivniKorisnikId}
          
          const requestOptions2 = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` },
            body: JSON.stringify(obavjestenje),
          };
      
          fetch(url2, requestOptions2)
        }
        setPostoji(!postoji)
    }

    const handleDodajNovuKol = () => {
      let url2 = "http://localhost:8080/kolekcije"
      let naziv= nazivKol
      let kolekcija = {naziv}
      
      const requestOptions2 = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` },
        body: JSON.stringify(kolekcija),
      };
  
      fetch(url2, requestOptions2).then(res => setRerender(!rerender))
    }

    const handleNaizvKolChange = event => {
        setNazivKol(event.target.value)
    }

    return (
        <div id="profil">
            <div id="naslov">
                <img id="slika-prof" src={korisnik.avatar}/>
                <div id="grupa">
                    <div className="podgrupa">
                        <p id="ime-profil">{korisnik.ime}{" "}{korisnik.prezime}</p>
                        {auth.res && email===auth.res.result.email && <Link to={`/profil/uredi`}><button id="podesavanja">Uredi profil</button></Link>}
                        {auth.res && email!==auth.res.result.email && <button id="podesavanja" onClick={handlePracenjeChange}>{postoji ? "Pracenje" : "Prati"}</button>}
                    </div>
                    <div className="podgrupa">
                        <p className="info">Pratioci{" "}{korisnik.brojPratioci}</p>
                        <p className="info">Prati{" "}{korisnik.brojPrati}</p>
                        {korisnik && korisnik.recepti && <p className="info">Objave{" "}{korisnik.recepti.length}</p> }
                    </div>
                </div>
            </div>
            {auth.res && email==auth.res.result.email && 
              <div id="podnaslov">
                <div className="divovi" onClick={handleClickRecepti}>
                    <p>Moji recepti</p>
                </div>
                <div className="divovi" onClick={handleClickSacuvani}>
                    <p>Sacuvani recepti</p>
                </div>
              </div>
            }
            { mojiRecepti &&    
            <div>
                {korisnik && korisnik.recepti && korisnik.recepti.map(recept => ( <RecipeCard recept={recept} /> ))}
            </div>
            }
            { !mojiRecepti && 
            <>
              <div>
                <input className="inputi-forme" type="text" placeholder="Naziv kolekcije" onChange={handleNaizvKolChange}/>
                <button className="dodaj-kol" onClick={handleDodajNovuKol}>Dodaj</button>
              </div>
              <div id="kolekcije-div">
                  {kolekcije.length>0 && kolekcije.map(kol => ( <KolekcijaCard kolekcija={kol} /> ))}
              </div>
            </>
            }
        </div>
    );
}

export default ShowProfile;