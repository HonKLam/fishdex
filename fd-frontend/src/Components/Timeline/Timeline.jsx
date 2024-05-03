import styles from '../../css/Timeline.module.css'
import Button from '../General/Button'
import RecentPost from './RecentPost'
import { useQuery } from 'react-query'
import { fetchData } from '../../utils/api'

export default function Timeline() {
    const { data, isLoading, isError } = useQuery('catch-data', () =>
        fetchData('/diary')
    )

    const user = useQuery('user-query', () => fetchData('/user/info/1'))

    if (isLoading) return <div></div>
    if (isError) return <div></div>

    function handleClick() {
        window.location.pathname = '/timeline/form'
    }

    return (
        <div className={styles.main_container}>
            <div className={styles.inner_container}>
                <span id={styles.bar_span}>
                    <h1 id={styles.bar_h1}>
                        {user.data && user.data.username}s Timeline
                    </h1>
                    <div id={styles.new_entry_btn}>
                        <Button text="Neuer Beitrag" callBack={handleClick} />
                    </div>
                </span>
                {data &&
                    user.data &&
                    data.map((entry, index) => {
                        return (
                            <RecentPost
                                key={index}
                                entry={entry}
                                user={user.data}
                            />
                        )
                    })}
            </div>
        </div>
    )
}
