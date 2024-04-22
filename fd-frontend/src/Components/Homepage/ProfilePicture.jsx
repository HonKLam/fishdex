import styles from '../../css/ProfilePicture.module.css'
import Fish from '/assets/Salmon.png'
import PropTypes from 'prop-types'

ProfilePicture.propTypes = {
    state: PropTypes.string,
}

export default function ProfilePicture(props) {
    const { state } = props

    return (
        <div
            className={
                state === 'small'
                    ? styles.small_container
                    : styles.main_container
            }
        >
            <img src={Fish} />
        </div>
    )
}
