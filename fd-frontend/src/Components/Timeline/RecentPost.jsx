import styles from '../../css/RecentPost.module.css'
import ProfilePicture from '../Homepage/ProfilePicture'
import Catch from '/assets/catch.jpg'

export default function RecentPost() {
    return (
        <div className={styles.main_container}>
            <div className={styles.rp_top}>
                <div className={styles.top_left}>
                    <div className={styles.row}>
                        <ProfilePicture state="small" />
                        <div className={styles.text}>
                            <h2>Lami Salami</h2>
                            <p>Gepostet am 09.07.2003</p>
                            <p>
                                ooga boogaooga boogaooga boogaooga boogaooga
                                boogaooga boogaooga boogaooga boogaooga
                                boogaooga boogaooga boogaooga boogaooga
                                boogaooga boogaooga boogaooga boogaooga
                                boogaooga boogaooga boogaooga boogaooga
                                boogaooga boogaooga boogaooga boogaooga
                                boogaooga boogaooga booga
                            </p>
                            <hr />
                        </div>
                    </div>
                </div>
            </div>

            <div className={styles.rp_bottom}>
                <div className={styles.bottom_left}>
                    <img id={styles.prevImg} src={Catch} />
                </div>
                <div className={styles.bottom_right}>
                    <h2>Königslachs</h2>
                    <p>erfolgreich gefangen!</p>
                    <br />
                    <p>Gewicht: 13,5kg</p>
                    <p>Länge: 100cm</p>
                    <br />
                    <p>Ort: Chemnitz</p>
                </div>
            </div>
        </div>
    )
}
