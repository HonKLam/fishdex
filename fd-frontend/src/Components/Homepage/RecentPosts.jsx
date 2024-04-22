import styles from '../../css/RecentPosts.module.css'
import Button from '../General/Button'

export default function RecentPosts() {
    function handleClick() {
        window.location.pathname = '/timeline'
    }
    return (
        <div className={styles.main_container}>
            <h3>Meine letzten Aktvitäten</h3>
            <Button text="Timeline" callBack={handleClick} />
        </div>
    )
}
