import '../Home/HomeStyle.css'
import { useState, useEffect } from "react";
import useAuth from '../hooks/useAuth'
import { useNavigate} from "react-router-dom";

function ShowSettings () {
    const {auth} = useAuth();
    const [ime, setIme] = useState("");
    const [prezime, setPrezime] = useState("");
    const [pol, setPol] = useState("");
    const [email, setEmail] = useState("");
    const [avatar, setAvatar] =useState("")
    const [id,setId] = useState("")
    const [staraLoz, setStaraLozinka] = useState("")
    const [novaLoz, setNovaLozinka] = useState("")
    const navigate = useNavigate();

    useEffect(() => {
        setId(auth.res.result.id)
        setIme(auth.res.result.ime)
        setPrezime(auth.res.result.prezime)
        setPol(auth.res.result.pol)
        setEmail(auth.res.result.email)
        setAvatar(auth.res.result.avatar)
    },[])

    const handleEmailChange = event => {
        setEmail(event.target.value);
      }
    
    const handleImeChange = event => {
        setIme(event.target.value);
      }
    
    const handlePrezimeChange = event => {
        setPrezime(event.target.value);
      }
    
    const handlePolChange = event => {
        setPol(event.target.value);
      }
    
    const handleAvatarChange = event => {
        setAvatar(event.target.value)
      }
    
    const handleStaraLozinkaChange = event => {
        setStaraLozinka(event.target.value)
      }
    
    const handleNovaLozinkaChange = event => {
        setNovaLozinka(event.target.value)
      }

    const handleSubmit = event => {
        event.preventDefault();
        let lozinka=auth.res.result.lozinka
        const korisnik = {id, ime,prezime,pol,email,lozinka,avatar}

        let url = "http://localhost:8080/korisnici"
        const requestOptions = {
            method: 'PUT',
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`},
            body: JSON.stringify(korisnik),
        };
        fetch(url, requestOptions).finally(navigate("/"))
    }

    const handlePromjenaLozinkeSubmit = event => {
        event.preventDefault();
        let staraLozinka=staraLoz
        let novaLozinka=novaLoz

        const lozinke={staraLozinka,novaLozinka}

        const url="http://localhost:8080/korisnici/promjenaLozinke"
        
        const requestOptions = {
          method: 'PUT',
          headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`},
          body: JSON.stringify(lozinke),
      };

        fetch(url, requestOptions).then(res =>{
          if(res)
            navigate("/")
        })
    }

    return (
      <>
      <div className="form">
        <form onSubmit={handleSubmit} >
          <p className="labele">Ime</p>
          <input className="inputi-forme" type="text" value={ime} onChange={handleImeChange} required />
          <p className="labele">Prezime</p>
          <input className="inputi-forme" type="text" value={prezime} onChange={handlePrezimeChange} required />
          <p className="labele">Pol</p>
          <div className="radio-inputi">
            <label className="radio-labele" for="muski" >Muski</label>
            <input className="radio-dugmad" type="radio" id="muski" value="Muski" name="pol" onChange={handlePolChange} />
            <label className="radio-labele" for="zenski">Zenski</label>
            <input className="radio-dugmad" type="radio" id="zenski" value="Zenski" name="pol" onChange={handlePolChange} />
            <label className="radio-labele" for="ostalo">Ostalo</label>
            <input className="radio-dugmad" type="radio" id="ostalo" value="Ostalo" name="pol" onChange={handlePolChange} />
          </div>
          <p className="labele">E-mail</p>
          <input className="inputi-forme" type="text" value= {email} onChange={handleEmailChange} required />
          <p className="labele">Avatar</p>
          <input className="inputi-forme" type="text" value= {avatar} onChange={handleAvatarChange} />
          <input className="submit-button" type="submit" value="Izmijeni" />
        </form>
      </div>
      <div className="form">
        <form onSubmit={handlePromjenaLozinkeSubmit}>
          <p className="labele">Stara lozinka</p>
          <input className="inputi-forme" type="password" onChange={handleStaraLozinkaChange} required />
          <p className="labele">Nova lozinka</p>
          <input className="inputi-forme" type="password" onChange={handleNovaLozinkaChange} required />
          <input className="submit-button" type="submit" value="Promijeni" />
        </form>
      </div>
      </>
    );
}

export default ShowSettings;