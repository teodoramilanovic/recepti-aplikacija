import './ProfileStyle.css'
import { Link } from "react-router-dom";

function KolekcijaCard({kolekcija}) {

    if(kolekcija.recepti.length > 4)
        kolekcija.recepti=kolekcija.recepti.slice(0,4);
    
    return (
        <Link to={`/kolekcija/${kolekcija.id}`} style={{textDecoration:'none', color:'black'}}>
            <div className="kolekcija">
                <div id="div-slike">
                    {kolekcija.recepti.length>0 && kolekcija.recepti.map(r=> (
                        <img className="slicica" src={r.recept.fotografija}/>
                    ))}
                </div>
                <p>{kolekcija.naziv}</p>
            </div>
        </Link>
    );
}

export default KolekcijaCard;