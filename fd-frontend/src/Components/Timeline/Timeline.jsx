import styles from '../../css/Timeline.module.css'
import Button from '../General/Button'
import RecentPost from './RecentPost'
import { useQuery } from 'react-query'
import { fetchData } from '../../utils/api'

export default function Timeline() {
    const { data, isLoading, isError } = useQuery('catch-data', () =>
        fetchData('/diary')
    )
    if (isLoading) return <div>Laden...</div>
    if (isError) return <div>Ein Fehler ist aufgetreten!</div>

    return (
        <div className={styles.main_container}>
            <div className={styles.inner_container}>
                <div>
                    <span id={styles.bar_span}>
                        <h1 id={styles.bar_h1}>Lamos Timeline</h1>
                        <div id={styles.new_entry_btn}>
                            <Button text="Neuer Beitrag" />
                        </div>
                    </span>
                    {data &&
                        data.map((entry, index) => {
                            return <RecentPost key={index} entry={entry} />
                        })}
                </div>
            </div>
        </div>
    )
}
