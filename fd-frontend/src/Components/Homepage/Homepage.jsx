import styles from '../../css/Homepage.module.css'
import ProfileHeader from './ProfileHeader'
import RecentPosts from './RecentPosts'

export default function Homepage() {
  return (
    <main className={styles.main_container}>
      <div className={styles.inner_container}>
        <ProfileHeader />
        <RecentPosts />
      </div>
    </main>
  )
}
