import styles from '../../css/ProfilePicture.module.css'
import PropTypes from 'prop-types'

ProfilePicture.propTypes = {
    state: PropTypes.string,
    image: PropTypes.string,
}

export default function ProfilePicture(props) {
    const { state, image } = props

    return (
        <div
            className={
                state === 'small'
                    ? styles.small_container
                    : styles.main_container
            }
        >
            <img src={image} />
        </div>
    )
}
