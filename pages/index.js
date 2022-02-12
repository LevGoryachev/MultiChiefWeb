import Head from 'next/head'
import Image from 'next/image'
import styles from '../styles/Home.module.css'

export default function Home() {
  return (
    <div className={styles.container}>
      <Head>
        <title>MultiChief</title>
        <meta name="description" content="MultiChief web-app" />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main className={styles.main}>
        <h1 className={styles.title}>
          Welcome to <a href="https://nextjs.org">MultiChief Web App!</a>
        </h1>

        <p className={styles.description}>
          Get started by editing{' '}
          <code className={styles.code}>pages/index.js</code>
        </p>

        <div className={styles.grid}>
          <a href="https://github.com/LevGoryachev/MultiChiefMain" className={styles.card}>
            <h2>MultiChiefMain &rarr;</h2>
            <p>Main project (orchestration services) of experimental ERP system MultiChief.</p>
          </a>

          <a href="https://github.com/LevGoryachev/MultiChiefConstruction" className={styles.card}>
            <h2>MultiChiefConstruction &rarr;</h2>
            <p>Construction site service (microservice)</p>
          </a>

          <a
            href="https://github.com/LevGoryachev/MultiChiefInventory" className={styles.card}>
            <h2>MultiChiefInventory &rarr;</h2>
            <p>Material resourse service (microservice).</p>
          </a>
        </div>
      </main>

      <footer className={styles.footer}>
        
      </footer>
    </div>
  )
}
