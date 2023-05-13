import "./HomeStyle.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import useAuth from '../hooks/useAuth'
import homeicon from '../slike/homeicon.png'
import notificon from '../slike/notificon.png'
import addicon from '../slike/addicon.png'
import logout from '../slike/logout.png'
import icon from '../slike/icon.png'
import { useNavigate} from "react-router-dom";

function NavBar() {
  const [unos, setUnos] = useState("");
  const {auth, setAuth} = useAuth();
  const navigate = useNavigate();

  const handleChange = event => {
    setUnos(event.target.value);
  }

  const handleLogout = () => {
    setAuth({})
    navigate("/login")
  }
  
  return (
    <div className="navbar">
        <Link to={`/`} style={{textDecoration: 'none'}}>
          <h1>Recepti</h1>
        </Link>
        <input id="search" type="search" onChange={handleChange} placeholder="Trazi..." autofocus required />
        <Link to={`/trazi/${unos}`}>
            <button id="search-button">Idi</button>
        </Link>
        {!auth.res && 
            <>
              <Link to={`/login`}>
                <button className="links"> Prijava </button>
              </Link>
              <Link to={`/signup`}>
                <button className="links"> Registracija </button>
              </Link>
            </>
          }
        {auth.res && 
            <>
              <Link to={`/pocetna`}>
                <img id="home-button" src={homeicon}/>
              </Link>
              <Link to={`/obavjestenja`}>
                <img id="notif-button" src={notificon}/>
              </Link>
              <Link to={`/dodaj`}>
                <img id="add-button" src={addicon}/>
              </Link>
              <img id="logout-button" onClick={handleLogout} src={logout}/>
              <Link to={`/profil/${auth.res.result.email}`}>
              <img id="profile-button" src={auth.res.result.avatar}/>
              </Link>
            </>
          }
    </div>
  );
}

export default NavBar;