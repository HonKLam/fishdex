import styles from '../../css/ProfileHeader.module.css'
import ProfilePicture from './ProfilePicture'
import ProfileText from './ProfileText'

export default function ProfileHeader() {
  return (
    <div className={styles.main_container}>
      <ProfilePicture />
      <ProfileText
        title="Lami Salami"
        description={
          'Turbo Loser, Kann das alles ne mehr, will einfach nur nach Hause oder einfach was essen heilige scheise hab ich hunger omg kann endlich Mittag sein'
        }
      />
    </div>
  )
}
