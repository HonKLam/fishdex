import styles from '../../css/ProfilePicture.module.css'
import Fish from '../../../public/assets/Salmon.png'

export default function ProfilePicture() {
  return (
    <div className={styles.main_container}>
      <img src={Fish} />
    </div>
  )
}
