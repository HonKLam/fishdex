import styles from '../../css/Button.module.css'
import PropTypes from 'prop-types'

Button.propTypes = {
    text: PropTypes.string,
    callBack: PropTypes.func,
}

export default function Button(props) {
    const { text, callBack } = props

    return (
        <div className={styles.main_container}>
            <button onClick={callBack}>{text}</button>
        </div>
    )
}
