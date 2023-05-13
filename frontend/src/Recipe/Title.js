import './RecipeStyle.css'

function Title({vrsta}) {
    let naslov="Popularni recepti"
    if(vrsta === "novi")
        naslov="Novi recepti"
    else if (vrsta === "trazi")
        naslov="Pretraga:"

    return (
        <p className="naslovi">{naslov}</p>
    );
}

export default Title;