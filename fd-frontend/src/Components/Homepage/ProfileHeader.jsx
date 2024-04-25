import styles from '../../css/ProfileHeader.module.css'
import ProfilePicture from './ProfilePicture'
import ProfileText from './ProfileText'
import { useQuery } from 'react-query'
import { fetchData } from '../../utils/api'

export default function ProfileHeader() {
    const { data, isLoading, isError } = useQuery('user-data', () =>
        fetchData('/user/info/1')
    )

    if (isLoading) return <div></div>
    if (isError) return <div></div>

    return (
        <div className={styles.main_container}>
            {data && <ProfilePicture image={data.imgUrl} />}
            <ProfileText
                title="Lami Salami"
                description={
                    'Turbo Loser, Kann das alles ne mehr, will einfach nur nach Hause oder einfach was essen heilige scheise hab ich hunger omg kann endlich Mittag sein'
                }
            />
        </div>
    )
}
