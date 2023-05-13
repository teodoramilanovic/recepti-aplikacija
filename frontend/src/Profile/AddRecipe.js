import { useState, useEffect} from "react";
import useAuth from '../hooks/useAuth'
import './ProfileStyle.css'
import { useNavigate} from "react-router-dom";

function AddRecipe () {
    const {auth} = useAuth();
    const [nazivR, setNaziv] = useState("")
    const [fotografijaR, setFotografija] = useState("")
    const [tezina, setTezina] = useState(null)
    const [postupak, setPostupak] = useState({})
    const [fotografije, setFotografije] = useState({})
    const [sastojciKolicina, setSastojciKolicina] = useState({})
    const [sastojciMJ, setSastojciMJ] = useState({})
    const [sastojciNaziv, setSastojciNaziv] = useState({})
    const [naziviSastojaka, setNaziviSastojaka] = useState({})
    const [tagovi, setTagovi] = useState({})
    const [naziviTagova, setNaziviTagova] = useState({})
    const [brojOsoba, setBrojOsoba] = useState(0);
    const [vrijemePripremeR, setVrijemePripreme] = useState(0)
    const [grupeJela, setGrupeJela] = useState([])
    const [oznaceneGrupe, setOznaceneGrupe] = useState({})
    const [brojac1Niz, setBrojac1Niz] = useState([1])
    const [brojac2Niz, setBrojac2Niz] = useState([1])
    const [brojac3Niz, setBrojac3Niz] = useState([1])
    const [preporuceniSastojci, setPreporuceniSastojci] = useState([])
    const [preporuceniTagovi, setPreporuceniTagovi] = useState([])
    const [current,setCurrent]=useState("")
    const [currentT,setCurrentT]=useState("")
    const navigate = useNavigate();

    useEffect(() => {
        let url = "http://localhost:8080/grupeJela"
        
        fetch(url).then((response) => {
            response.json().then((result) => {
                setGrupeJela(result);
              });
            })
          
      }, []);

    const handleNazivChange = event => {
        setNaziv(event.target.value);
    }

    const handleFotografijaChange = event => {
        setFotografija(event.target.value);
    }

    const handleTezinaChange = event => {
        setTezina(event.target.value);
      }
    
    const handleBrojOsobaChange = event => {
        setBrojOsoba(parseInt(event.target.value))
    }

    const handleVrijemePripremeChange = event => {
        setVrijemePripreme(parseFloat(event.target.value))
    }

    const handleOznaceneGrupe = event => {
        setOznaceneGrupe(prethodni => ({...prethodni, [event.target.id]:!prethodni[event.target.id]}))
    }

    const handlePostupakChange = event => {
        let naziv = event.target.id+""
        setPostupak(prethodni => ({...prethodni,[naziv]:event.target.value}))
    }

    const handleFotografijaPostupkaChange = event => {
        let naziv = event.target.id+""
        setFotografije(prethodni => ({...prethodni,[naziv]:event.target.value}))
    }

    const handleSastojakKolicinaChange = event => {
        let naziv = event.target.id+""
        setSastojciKolicina(prethodni => ({...prethodni,[naziv]:event.target.value}))
    }

    const handleSastojakMJChange = event => {
        let naziv = event.target.id+""
        setSastojciMJ(prethodni => ({...prethodni,[naziv]:event.target.value}))
    }
    
    const handleSastojakNazivChange = event => {
        let naziv = event.target.id+""
        setSastojciNaziv(prethodni => ({...prethodni,[naziv]:"naziv "+event.target.value}))
        setCurrent(naziv)
        setNaziviSastojaka(prethodni => ({...prethodni,[naziv]:event.target.value}))
        
        if(event.target.value==""){
            setPreporuceniSastojci([])
        }else{
            let url = "http://localhost:8080/sastojci/trazi/"+event.target.value
            fetch(url).then((response) => {
                response.json().then((result) => {
                    setPreporuceniSastojci(result);
                });
            })
        }
        
    }

    const handleTagChange = event => {
        let naziv = event.target.id+""
        setTagovi(prethodni => ({...prethodni,[naziv]:"naziv "+event.target.value}))
        setCurrentT(naziv)
        setNaziviTagova(prethodni => ({...prethodni,[naziv]:event.target.value}))

        if(event.target.value==""){
            setPreporuceniTagovi([])
        }else{
            let url = "http://localhost:8080/tagovi/trazi/"+event.target.value
            fetch(url).then((response) => {
                response.json().then((result) => {
                    setPreporuceniTagovi(result);
                });
            })
        }
    }

    const handleKliknutSastojak = event => {
        let naziv = event.target.id+""
        setSastojciNaziv(prethodni => ({...prethodni,[naziv]:"id "+event.target.value}))
        setPreporuceniSastojci([])
        setNaziviSastojaka(prethodni => ({...prethodni,[naziv]:event.target.name}))
    }

    const handleKliknutTag = event => {
        let naziv = event.target.id+""
        setTagovi(prethodni => ({...prethodni,[naziv]:"id "+event.target.value}))
        setPreporuceniTagovi([])
        setNaziviTagova(prethodni => ({...prethodni,[naziv]:event.target.name}))
    }

    const handleDodajPostupak = () => {
        setBrojac1Niz(prethodni => [...prethodni, 1])
    }

    const handleDodajSastojak = () => {
        setBrojac2Niz(prethodni => [...prethodni, 1])
    }

    const handleDodajTag = () => {
        setBrojac3Niz(prethodni => [...prethodni, 1])
    }

    const funS = async (nazivS) => {
        let url = "http://localhost:8080/sastojci" 
        let naziv=nazivS
        let sastojak = {naziv}
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`},
            body: JSON.stringify(sastojak),
        };
        const response= await fetch(url, requestOptions)
        if(!response.ok){
            throw new Error('data could not be fetched')
        }else{
            return response.json()
        }
    }

    const funN = async (id, key, recId) => {
        let url = "http://localhost:8080/sastojciRecepta" 
        let kolicina = sastojciKolicina[key]
        let mjernaJedinica = sastojciMJ[key]
        let sastojakId = id
        let receptId = recId;
        let sastojakRecepta = {kolicina, mjernaJedinica, sastojakId, receptId}
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`},
            body: JSON.stringify(sastojakRecepta),
        };
        fetch(url, requestOptions)
    }

    const funS2 = async (nazivS) => {
        let url = "http://localhost:8080/tagovi" 
        let naziv=nazivS
        let tag = {naziv}
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`},
            body: JSON.stringify(tag),
        };
        const response= await fetch(url, requestOptions)
        if(!response.ok){
            throw new Error('data could not be fetched')
        }else{
            return response.json()
        }
    }

    const funN2 = async (id, recId) => {
        let url = "http://localhost:8080/tagoviRecepta" 
        let tagId = id
        let receptId = recId;
        let tagRecepta = {tagId, receptId}
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`},
            body: JSON.stringify(tagRecepta),
        };
        fetch(url, requestOptions)
    }

    const funDodajRecept = async () => {
        let url = "http://localhost:8080/recepti" //https://img.taste.com.au/nyBI7B1_/taste/2017/08/steak-with-mushroom-sauce-129161-2.jpg
        let naziv=nazivR;
        let vrijemePripreme=vrijemePripremeR
        let fotografija=fotografijaR
        let brojPorcija=brojOsoba
        let tezinaPripreme=tezina
        let recept = {naziv,vrijemePripreme,fotografija,brojPorcija,tezinaPripreme}

        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`},
            body: JSON.stringify(recept),
        };

        const response= await fetch(url, requestOptions)
        if(!response.ok){
            throw new Error('data could not be fetched')
        }else{
            return response.json()
        }
    }
    const funDodajOstalo = async (recept) => {
        Object.entries(oznaceneGrupe).map(([key,value],i) => {
            if(value){
                let url1 = "http://localhost:8080/grupeJelaRecepta"
                let grupaJelaId=parseInt(key);
                let receptId=recept.id;
                let grupaJelaRecepta={grupaJelaId,receptId}
                const requestOptions1 = {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`},
                    body: JSON.stringify(grupaJelaRecepta),
                };
                fetch(url1, requestOptions1)
            }
        })

        Object.entries(postupak).map(([key,value],i) => {
            let url2 = "http://localhost:8080/postupciPripreme/" + recept.id
            let opis = value
            let redniBroj = parseInt(key)+1
            let fotografija = fotografije[key]
            let postupakPripreme = {opis, redniBroj, fotografija}
            const requestOptions2 = {
                method: 'POST',
                headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${auth.res.token}`},
                body: JSON.stringify(postupakPripreme),
            };
            fetch(url2, requestOptions2)
        })

        Object.entries(sastojciNaziv).map(([key,value],i) => {
            
            if(value.includes("id ")){
                funN(parseInt(value.substring(3,value.length)),key, recept.id)
            }else{
                funS(value.substring(6,value.length)).then((res) => {
                    funN(res.id, key, recept.id)
                  }).catch((e) => console.log(e.message))
            }
            
        })

        Object.entries(tagovi).map(([key,value],i) => {
            
            if(value.includes("id ")){
                funN2(parseInt(value.substring(3,value.length)), recept.id)
            }else{
                funS2(value.substring(6,value.length)).then((res) => {
                    funN2(res.id, recept.id)
                  }).catch((e) => console.log(e.message))
            }
            
        })
    }
    const handleObjaviRecept = () => {
        funDodajRecept().then((res) => {
            funDodajOstalo(res)
          }).then(() => 
          navigate("/profil/"+auth.res.result.email)).catch((e) => console.log(e.message))
    }

    return (
        <div id="dodaj-recept">
            <div id="prvi-dio">
                <p className="naslovi">Naziv recepta</p>
                <input className="inputi-prvi" type="text"  onChange={handleNazivChange} required />
                <p className="naslovi">Dodaj fotografiju</p>
                <input className="inputi-prvi" type="text"  onChange={handleFotografijaChange}/>
            </div>
            <div id="prvi-dio">
                <p className="naslovi">Broj osoba</p>
                <input className="inputi-drugi" type="text"  onChange={handleBrojOsobaChange}/>
                <p className="naslovi">Vrijeme pripreme u minutama</p>
                <input className="inputi-drugi" type="text"  onChange={handleVrijemePripremeChange}/>
            </div>
            <div id="treci-dio">
                <p className="naslovi">Grupe jela</p>
                {grupeJela && grupeJela.map(gj => 
                    <div id="grupe-jela">
                    <label className="gj-labele" for={gj.naziv} >{gj.naziv}</label>
                    <input className="gj-chcbox" type="checkbox" id={gj.id} value={gj.naziv} onChange={handleOznaceneGrupe}/>
                    </div>
                )}
            </div>
            <div id="cetvrti-dio">
                <p className="naslovi">Tezina pripreme</p>
                <div className="radio-inputi">
                    <label className="radio-labele" for="jednostavno" >Jednostavno</label>
                    <input className="radio-dugmad" type="radio" id="jednostavno" value="Jednostavno" name="tezina" onChange={handleTezinaChange} />
                    <label className="radio-labele" for="srednje_zahtjevno">Srednje zahtjevno</label>
                    <input className="radio-dugmad" type="radio" id="srednje_zahtjevno" value="Srednje zahtjevno" name="tezina" onChange={handleTezinaChange} />
                    <label className="radio-labele" for="slozeno">Slozeno</label>
                    <input className="radio-dugmad" type="radio" id="slozeno" value="Slozeno" name="tezina" onChange={handleTezinaChange} />
                </div>
            </div>
            <div id="peti-dio">
                <div id="peti-dio-pod">
                    <p className="naslovi">Sastojci</p>
                    <button className="plus" onClick={handleDodajSastojak}>Dodaj</button>
                </div>
                {brojac2Niz.map((broj,indeks) =>
                    <> 
                    <div className="sastojci">
                        <input className="kolicine" id={indeks} type="text"  onChange={handleSastojakKolicinaChange} placeholder="Kolicina" />
                        <input className="kolicine" id={indeks} type="text"  onChange={handleSastojakMJChange} placeholder="Mjerna jedinica" />
                        <input className="inputi-prvi" id={indeks} type="text" value={naziviSastojaka[indeks]} onChange={handleSastojakNazivChange} placeholder="Sastojak" />
                    </div>
                    {preporuceniSastojci.length>0 && current==indeks && 
                        <div className="prikaz-el" >
                            {preporuceniSastojci.map(p => <button className="prep-sastojci" id={indeks} value={p.id} name={p.naziv} onClick={handleKliknutSastojak}>{p.naziv}</button>)}
                        </div>
                    }
                    </>
                )}
            </div>
            <div id="peti-dio">
                <div id="peti-dio-pod">
                    <p className="naslovi">Priprema</p>
                    <button className="plus" onClick={handleDodajPostupak}>Dodaj</button>
                </div>
                <div id="postupci-ta">
                {brojac1Niz.map((broj, indeks) =>
                    <div className="pripr-div"> 
                        <textarea className="ta" id={indeks} onChange={handlePostupakChange} placeholder="Opis koraka pripreme..."></textarea>
                        <div id="dodaj-fot">
                            <label className="radio-labele" for={indeks} >Dodaj fotografiju</label>
                            <input className="inputi-prvi" id={indeks} type="text" onChange={handleFotografijaPostupkaChange} />
                        </div>
                    </div>
                )}
                </div>   
            </div>
            <div id="peti-dio">
                <div id="peti-dio-pod">
                    <p className="naslovi">Tagovi</p>
                    <button className="plus" onClick={handleDodajTag}>Dodaj</button>
                </div>
                {brojac3Niz.map((broj,indeks) => 
                    <>
                    <div id="tag-div">
                        <input id={indeks} className="inputi-prvi" type="text" value={naziviTagova[indeks]} onChange={handleTagChange} placeholder="Dodaj tag" />
                    </div>
                    {preporuceniTagovi.length>0 && currentT==indeks && 
                    <div className="prikaz-el2">
                        {preporuceniTagovi.map(t => <button className="prep-sastojci" id={indeks} value={t.id} name={t.naziv} onClick={handleKliknutTag}>{t.naziv}</button>)}
                    </div>
                    }
                    </>
                )}
            </div>
            <button id="dugme-objavi" onClick={handleObjaviRecept}>Objavi recept</button>
        </div>
    );
}

export default AddRecipe;