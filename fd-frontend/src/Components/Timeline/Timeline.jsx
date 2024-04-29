import styles from '../../css/Timeline.module.css'
import Button from '../General/Button'
import RecentPost from './RecentPost'

export default function Timeline() {
    function handleClick () {
        window.location.pathname = '/timeline/form'
    }
    return (
        <div className={styles.main_container}>
            <div className={styles.inner_container}>
                <span id={styles.bar_span}>
                    <h1 id={styles.bar_h1}>Lamos Timeline</h1>
                    <div id={styles.new_entry_btn}>
                        <Button text="Neuer Beitrag" callBack={handleClick}/>
                    </div>
                </span>
                <RecentPost />
                <RecentPost />
                <RecentPost />
            </div>
        </div>
    )
}
