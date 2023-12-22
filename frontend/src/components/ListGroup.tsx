import { Fragment} from "react";

function ListGroup() {
    const items = [
        "Kaunas",
        "Vilnius",
        "Šiauliai"
    ];

    let selectedIndex = 0;
    // const arr = useState(-1)

    const getMsg = () => {
        return items.length === 0 ? <p>Nothing</p> : null
    }

    return (
        <Fragment>
            <h1>List</h1>
            {getMsg()}
            <ul className="list-group list-group-flush">
                {items.map((item, index) => <li key={item} onClick={()=> selectedIndex = index} className={selectedIndex === index ? "list-group-item active" : "list-group-item "}>{item}</li>)}
            </ul>
        </Fragment>
    );
}

export default ListGroup;