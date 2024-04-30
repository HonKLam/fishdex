import styles from '../../css/Homepage.module.css'
import ProfileHeader from './ProfileHeader'
import RecentPosts from './RecentPosts'
import { fetchData } from '../../utils/api'
import { useQuery } from 'react-query'

export default function Homepage() {
    const { data, isLoading, isError } = useQuery('user-data', () =>
        fetchData('/user/info/1')
    )

    if (isLoading) return <div></div>
    if (isError) return <div></div>

    return (
        <main className="section">
            {data && (
                <div className="container">
                    <ProfileHeader user={data} />
                    <RecentPosts user={data} />
                </div>
            )}
        </main>
    )
}
