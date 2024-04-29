import styles from '../../css/ProfileHeader.module.css'
import ProfilePicture from './ProfilePicture'
import ProfileText from './ProfileText'
import PropTypes from 'prop-types'

ProfileHeader.propTypes = {
    user: PropTypes.object,
}

export default function ProfileHeader(props) {
    const { user } = props

    return (
        <div className={styles.main_container}>
            <ProfilePicture image={user.imgUrl} />
            <ProfileText title={user.username} description={user.description} />
        </div>
    )
}
