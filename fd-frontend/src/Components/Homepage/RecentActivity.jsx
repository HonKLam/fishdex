import styles from '../../css/RecentActivity.module.css'
import ProfilePicture from './ProfilePicture'
import PropTypes from 'prop-types'
import formatDate from '../../utils/date'

RecentActivity.propTypes = {
    user: PropTypes.object,
    entry: PropTypes.object,
}

export default function RecentActivity(props) {
    const { user, entry } = props

    const creationDate = formatDate(entry.createdOn)

    return (
        <div className={styles.activity_container}>
            <div id={styles.profile_img}>
                <ProfilePicture state="small" image={user.imgUrl} />
            </div>
            <div className={styles.ra_text}>
                <h1>{user.username}</h1>
                <p>Hat einen neuen Fisch gefangen!</p>
            </div>
            <div id={styles.date}>
                <p>am {creationDate}</p>
            </div>
        </div>
    )
}
