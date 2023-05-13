import './HomeStyle.css'
import { useState } from "react";
import useAuth from '../hooks/useAuth'
import { useNavigate} from "react-router-dom";

function SignUpForm() {
  const {setAuth} = useAuth();
  const [ime, setIme] = useState("");
  const [prezime, setPrezime] = useState("");
  const [pol, setPol] = useState("");
  const [email, setEmail] = useState("");
  const [lozinka, setLozinka] = useState("");
  const [error,setError] = useState("")
  const navigate = useNavigate();

function isValidEmail(email){
  return /\S+@\S+\.\S+/.test(email)
}

  const fun1 = async () => {
    let url = "http://localhost:8080/korisnici/jeDostupan/"+email

    const response= await fetch(url)
        if(!response.ok){
          throw new Error('data could not be fetched')
        }else{
          console.log(response)
          return response.json()
        }
  }

  const fun2 = async (res) => {
    if(res){
      setError("")
      let url = "http://localhost:8080/korisnici/register"

      let avatar="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtNWVnKZZfy-1CLo75eO5vLhTWFZyeyc7QaI6GgdSalXDIJOCA6t0DSdDDMabrTOdjdYs&usqp=CAU"
      let korisnik = {ime,prezime,pol,email,lozinka, avatar}
      
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(korisnik),
      };

      fetch(url, requestOptions).then(res => res.json()).then(res => setAuth({res})).finally(navigate("/"))
    }else{
      setError("Korisnik sa tim e-mailom vec postoji!")
    }
  }

  const handleSubmit = event => {
    event.preventDefault();

    if(isValidEmail(email)){
      setError("")
      fun1().then((res) => {
        fun2(res)
      }).catch((e) => console.log(e.message))
    }else{
      setError("E-mail nije validan!")
    }

  }

  const handleEmailChange = event => {
    setEmail(event.target.value);
  }

  const handleLozinkaChange = event => {
    setLozinka(event.target.value);
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

    return (
      <div className="form">
        <form onSubmit={handleSubmit} >
          <p className="labele">Ime</p>
          <input className="inputi-forme" type="text" onChange={handleImeChange} required />
          <p className="labele">Prezime</p>
          <input className="inputi-forme" type="text" onChange={handlePrezimeChange} required />
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
          <input className="inputi-forme" type="text" onChange={handleEmailChange} required />
          <p className="labele">Lozinka</p>
          <input className="inputi-forme" type="password" onChange={handleLozinkaChange} required />
          <input className="submit-button" type="submit" value="Registruj se" />
          {error!="" && <p>{error}</p>}
        </form>
      </div>
    );
  }
  
  export default SignUpForm;