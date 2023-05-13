import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import useAuth from '../hooks/useAuth'
import { useNavigate} from "react-router-dom";
import './RecipeStyle.css'

function ShowRecipe () {
    const { id } = useParams();
    const [recept, setRecept] = useState({});
    const [kolekcije, setKolekcije] = useState([]);
    const [prikazi1, setPrikazi1] = useState(false);
    const [prikazi2, setPrikazi2] = useState(false);
    const [prikazi3, setPrikazi3] = useState(false);
    const [prikazi4, setPrikazi4] = useState(false);
    const [tekst, setTekst] = useState("");
    const {auth} = useAuth();
    const [rerender, setRerender] = useState(false)
    const navigate = useNavigate();

    useEffect(() => {
        if (id) {
          let url1 = "http://localhost:8080/recepti/" + id;
          fetch(url1).then((response) => {
            response.json().then((result) => {
              setRecept(result);
            });
          });
        
        if(auth.res){
          let url2 = "http://localhost:8080/mojeKolekcije"
          const requestOptions = {
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`}
          };
            fetch(url2,requestOptions).then((response) => {
              response.json().then((result) => {
                setKolekcije(result);
              });
            })
          }
        }
      }, [rerender]);

    const handleNapisiKomentar = () => {
      if(auth.res)
        setPrikazi1(!prikazi1);
      else
        setPrikazi2(true);
    }

    const handlePrikaziKolekcije = () => {
      if(auth.res){
        setPrikazi3(!prikazi3);
      }
      else
        setPrikazi4(true);
    }

    const handleSacuvaj = (event) => {
      let kolekcijaId=event.target.value
      console.log(kolekcijaId)
      let receptId=recept.id
      let url = "http://localhost:8080/omiljeniRecepti"
      
      let omiljenRecept = {receptId,kolekcijaId}

      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` },
        body: JSON.stringify(omiljenRecept),
      };
  
      fetch(url, requestOptions).finally(() => setPrikazi3(!prikazi3))
    }

    const handlePosaljikomentar = () => {
      let korisnikId = auth.res.result.id
      let receptId = id
      let url1 = "http://localhost:8080/komentari"

      let komentar = {tekst, korisnikId, receptId}
      
      const requestOptions1 = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` },
        body: JSON.stringify(komentar),
      };
  
      fetch(url1, requestOptions1)

      let url2 = "http://localhost:8080/obavjestenja"
      let opis="je komentarisao/la Vasu objavu."
      let aktivniKorisnikid = auth.res.result.id
      let pasivniKorisnikId = recept.korisnik.id
      let obavjestenje = {opis, aktivniKorisnikid, pasivniKorisnikId}
      console.log(obavjestenje)
      const requestOptions2 = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` },
        body: JSON.stringify(obavjestenje),
      };
  
      fetch(url2, requestOptions2).finally(() => setRerender(!rerender))
    }

    const handleTekstChange = (event) => {
        setTekst(event.target.value);
    }

    const funObrisi1 = async () => {
      recept.tagovi.map(rt => {
        let url = "http://localhost:8080/tagoviRecepta/"+ rt.id

        const requestOptions = {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` }
        };
    
        fetch(url, requestOptions)
      })

      recept.komentari.map(rk => {
        let url = "http://localhost:8080/komentari/"+ rk.id

        const requestOptions = {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` }
        };
    
        fetch(url, requestOptions)
      })

      recept.postupciPripreme.map(rpp => {
        let url = "http://localhost:8080/postupciPripreme/"+ rpp.id

        const requestOptions = {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` }
        };
    
        fetch(url, requestOptions)
      })

      recept.sastojci.map(rs => {
        let url = "http://localhost:8080/sastojciRecepta/"+ rs.id

        const requestOptions = {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` }
        };
    
        fetch(url, requestOptions)
      })

      let url1 = "http://localhost:8080/omiljeniRecepti/recept/"+ recept.id

      const requestOptions1 = {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` }
      };
    
      fetch(url1, requestOptions1)

      let url2 = "http://localhost:8080/grupeJelaRecepta/recept/"+ recept.id

      const requestOptions2 = {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` }
      };
    
      fetch(url2, requestOptions2)

      return true
    }

    const funObrisi2 = async (vrijednost) => {
      let url = "http://localhost:8080/recepti/"+ recept.id

      const requestOptions = {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}` }
      };
  
      fetch(url, requestOptions)
    }

    const handleObrisiRecept = () => {
      funObrisi1().then((res) => {
        funObrisi2(res)
      }).then(() => 
      navigate("/")).catch((e) => console.log(e.message))
    }

    return (
      <div id="prikaz">
        { recept && auth.res && recept.korisnik && auth.res.result.id===recept.korisnik.id && 
          <div id="div-buttona">
            <button id="brisi-dugme" style={{marginLeft:'63%'}} onClick={handleObrisiRecept}>Obrisi recept</button>
          </div>
        }
        <div id="prikaz-recepta">
            {recept && <img id="slika-recepta" src={recept.fotografija} alt={recept.naziv} />}
            <div id="info">
              <div id="grupa-info">
                {recept && recept.korisnik && 
                  <Link to={`/profil/${recept.korisnik.email}`} >
                    <img id="slika-korisnika" src={recept.korisnik.avatar} />
                  </Link>
                }
                <div>
                  {recept && recept.korisnik && <p style={{fontSize: 18, fontWeight: '600'}}>{recept.korisnik.ime}{" "}{recept.korisnik.prezime}</p> }
                  {recept && <p style={{fontSize: 18, fontWeight: '600'}}>{recept.naziv}</p> }
                </div>
                <button id="info-dugme" onClick={handlePrikaziKolekcije}>Sacuvaj recept</button>
              </div>
                {recept && recept.tezinaPripreme && <p>Tezina pripreme - {recept.tezinaPripreme}</p> }
                {recept && recept.vrijemePripreme && <p>Vrijeme pripreme - {recept.vrijemePripreme} minuta</p> }
                {recept && recept.brojPorcija && <p>Broj porcija - {recept.brojPorcija}</p> }
            </div>
            {prikazi3 && <div id="kol-prikaz">
                <p id="nasl">Sacuvaj recept u kolekciji:</p>
                {kolekcije && kolekcije.map(k => <button className="imena" value={k.id} onClick={handleSacuvaj}>{k.naziv}</button>)}
            </div> }
            {prikazi4 && <div id="kol-prikaz">
                <p>Morate biti prijavljeni da sacuvate recept!</p>
            </div> }
            <p className="naslovi">Sastojci</p>
            <div id="sastojci">
                {recept && recept.sastojci && recept.sastojci.map(s => (<p>{s.kolicina}{" "}{s.mjernaJedinica}{" "}{s.sastojak.naziv}</p>))}
            </div>
            <p className="naslovi">Postupci pripreme</p>
            <div id="postupci">
                {recept && recept.postupciPripreme && recept.postupciPripreme.map(pp => (
                  <div id="postupak">
                    <p id="pp-naslov">{pp.redniBroj}</p>
                    <div id="pp-sire">
                      <p>{pp.opis}</p>
                      {pp.fotografija && <img id="pp-slika" src={pp.fotografija} alt={pp.redniBroj}></img>}
                    </div>
                  </div>
                ))}
            </div>
            <p className="naslovi">Tagovi</p>
            <div id="tagovi">
            {recept && recept.tagovi && recept.tagovi.map(t => (
                  <Link to={`/tagovi/${t.tag.id}`}>
                    <button className="tag-dugme"> #{t.tag.naziv} </button>
                  </Link>
                ))}
            </div>
            <p className="naslovi">Grupe jela</p>
            <div id="tagovi">
            {recept && recept.grupeJela && recept.grupeJela.map(gj => (
                  <Link to={`/grupeJela/${gj.grupaJela.id}`}>
                    <button className="gj-dugme"> {gj.grupaJela.naziv} </button>
                  </Link>
                ))}
            </div>
            <div id="kom-naslov">
              {recept && recept.komentari && <p className="naslovi" style={{marginRight:30}}>{recept.komentari.length}</p>}
              <p className="naslovi" style={{marginRight:350}}>Komentara</p>
              <button id="kom-dugme" onClick={handleNapisiKomentar}> Napisi komentar</button>
            </div>
            <div id="komentari">
              {prikazi1 && 
                <div className="prikazi">
                  <textarea id="tekst" onChange={handleTekstChange} placeholder="Napisi komentar..."></textarea>
                  <button id="koment-dugme" onClick={handlePosaljikomentar}>Komentarisi</button>
                </div>
              }
              {prikazi2 && 
                <div className="prikazi">
                  <p id="upozorenje">Morate da se prijavite kako biste ostavili komentar!</p>
                </div>
              }
            {recept && recept.komentari && recept.komentari.map(k => (
                  <div className="komentar">
                    {k.korisnik && <p>{k.korisnik.ime}{" "}{k.korisnik.prezime}</p>}
                    <p>{k.datumObjave.substring(0,10)}{" "}{k.datumObjave.substring(11,19)}</p>
                    <p style={{marginTop:5, color:'gray'}}>{k.tekst}</p>
                  </div>
                ))}
            </div>
        </div>
      </div>
    );
}

export default ShowRecipe;