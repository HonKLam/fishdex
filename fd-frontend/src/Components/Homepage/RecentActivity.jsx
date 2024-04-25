import styles from '../../css/RecentActivity.module.css'
import PropTypes from 'prop-types'
import ProfilePicture from './ProfilePicture'

RecentActivity.propTypes = {
    user: PropTypes.object,
}

export default function RecentActivity(props) {
    const { user } = props

    return (
        <div className={styles.activity_container}>
            <div id={styles.profile_img}>
                <ProfilePicture state="small" image={'/assets/Salmon.png'} />
            </div>
            <div className={styles.ra_text}>
                <h1>Lami Salami</h1>
                <p>Hat einen neuen Fisch gefangen!</p>
            </div>
            <div id={styles.date}>
                <p>am 09.07.2003</p>
            </div>
        </div>
    )
}
