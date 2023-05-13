import './HomeStyle.css'
import { useState} from "react";
import useAuth from '../hooks/useAuth'
import { useNavigate} from "react-router-dom";

function LogInForm() {
  const {setAuth} = useAuth();
  const [email, setEmail] = useState("");
  const [lozinka, setLozinka] = useState("");
  const [error,setError] = useState("")
  const navigate = useNavigate();

  const fun1 = async () => {
    let url = "http://localhost:8080/korisnici/login"

    let korisnik = {email,lozinka}
    
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(korisnik),
    };

    const response= await fetch(url,requestOptions)
        if(!response.ok){
          throw new Error('data could not be fetched')
        }else{
          return response.json()
        }
  }

  const fun2 = async (res) => {
    if(res.result){
      setError("")
      setAuth({res})
      navigate("/")
    }else{
      setError("Netacna lozinka!")
    }
  }

  const handleSubmit = event => {
    event.preventDefault();
    
    fun1().then((res) => {
      fun2(res)
    }).catch((e) => console.log(e.message))
  }

  const handleEmailChange = event => {
    setEmail(event.target.value);
  }

  const handleLozinkaChange = event => {
    setLozinka(event.target.value);
  }

    return (
      <div className="form">
        <form onSubmit={handleSubmit} >
          <p className="labele">E-mail</p>
          <input className="inputi-forme" type="text"  onChange={handleEmailChange} required />
          <p className="labele">Lozinka</p>
          <input className="inputi-forme" type="password" onChange={handleLozinkaChange} required />
          <input className="submit-button" type="submit" value="Prijavi se" />
          {error!="" && <p>{error}</p>}
        </form>
      </div>
    );
  }
  
  export default LogInForm;