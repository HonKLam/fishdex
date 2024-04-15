export default function Header() {
    return (
        <div className="header">
            <div className="container">
                <div className="row">
                    <a href="/" className="logo">
                        <img src="./../../../assets/fishing-hook.svg" />
                    </a>
                    <div className="menu">
                        <ul>
                            <li>
                                <a>Fishdex</a>
                            </li>
                            <li>
                                <a>Blogeinträge</a>
                            </li>
                            <li>
                                <a>und nochirgendwas</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}
