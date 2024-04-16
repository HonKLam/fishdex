import styles from '../../css/ProfileText.module.css'
import Button from './Button'
import PropTypes from 'prop-types'

ProfileText.propTypes = {
  title: PropTypes.string,
  description: PropTypes.string,
}

export default function ProfileText(props) {
  const { title, description } = props

  function handleClick() {
    console.log('der button wurde gedrückt')
  }

  return (
    <div className={styles.main_container}>
      <div className={styles.field}>
        <h1>{title}</h1>
        <hr />
        <p>{description}</p>
        <Button text="Fishdex" callBack={handleClick} />
      </div>
    </div>
  )
}
